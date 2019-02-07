package com.nazeer.vacinationreminder.Data

import androidx.lifecycle.LiveData
import com.nazeer.vacinationreminder.Commons.VaccinationUtils
import com.nazeer.vacinationreminder.Notifications.NotifyManager
import org.jetbrains.anko.doAsync


class Repo(db: DB) {

    private var childDao: ChildDao
    private var vaccinationDao: VaccinationDao

    fun getChildren(): LiveData<List<Child>> {
        return childDao.getAllChildren()
    }

    fun getChild(childId: Long) = childDao.getChild(childId)

    fun insertChild(child: Child) {
        doAsync {
           val id = childDao.insert(child)
            child.id=id
            val vaccinations = VaccinationUtils.createNeededVaccination(child.birthDate,id)
            vaccinationDao.insert(vaccinations )
            for (va in vaccinations){
                NotifyManager.scheduleNotification(child,va)
            }
        }

    }



     fun getVaccinationsForChild(childId:Long): LiveData<List<Vaccination>> {
        return vaccinationDao.getVaccinationForChild(childId)
     }

    companion object {
        private var INSTANCE: Repo? = null

        fun init(db: DB): Repo {
            if (INSTANCE == null) {
                synchronized(Repo::class) {
                    INSTANCE =
                        Repo(db)
                }
            }
            return INSTANCE!!
        }

        fun getInstance(): Repo {
            if (INSTANCE == null) throw IllegalStateException("cant get instance before initializing")
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    init {
        childDao = db.childrenDAO()
        vaccinationDao = db.vaccinationDAO()
    }
}