package com.jmarser.vehiclemanager.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.jmarser.vehiclemanager.data.dataSource.AuthRemoteDataSource
import com.jmarser.vehiclemanager.data.mapper.toUserData
import com.jmarser.vehiclemanager.data.model.AuthCredentialsData
import com.jmarser.vehiclemanager.data.model.LoginData
import com.jmarser.vehiclemanager.data.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Project: Vehicle manager
 * File: FirebaseAuthDataSourceImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

@Singleton
class FirebaseAuthDataSourceImpl
    @Inject
    constructor(
        private val firebaseAuth: FirebaseAuth,
    ) : AuthRemoteDataSource {
        override fun login(loginData: LoginData): Flow<Result<UserData>> =
            flow {
                try {
                    val result = firebaseAuth.signInWithEmailAndPassword(loginData.email, loginData.password).await()
                    val userData = result.user?.toUserData()
                    if (userData != null) {
                        emit(Result.success(userData))
                    } else {
                        emit(Result.failure(Exception("Usuario no encontrado")))
                    }
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun register(authCredentials: AuthCredentialsData): Flow<Result<UserData>> =
            flow {
                try {
                    val result = firebaseAuth.createUserWithEmailAndPassword(authCredentials.email, authCredentials.password).await()

                    result.user?.let { user ->
                        val profileUpdate =
                            UserProfileChangeRequest
                                .Builder()
                                .setDisplayName(authCredentials.name)
                                .build()

                        user.updateProfile(profileUpdate).await()

                        emit(Result.success(user.toUserData()))
                    } ?: run {
                        emit(Result.failure(Exception("Error al registrar usuario")))
                    }
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun forgotPassword(email: String): Flow<Result<Unit>> =
            flow {
                try {
                    firebaseAuth.sendPasswordResetEmail(email).await()
                    emit(Result.success(Unit))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }
    }
