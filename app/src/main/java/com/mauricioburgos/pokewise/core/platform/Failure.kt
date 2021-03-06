package com.mauricioburgos.pokewise.core.platform
import com.mauricioburgos.pokewise.domain.ErrorResponse

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    class ServerError (val errorResponse: ErrorResponse) : Failure()
    object DatabaseError : Failure()
    object LastPage: Failure()
    object IsLoading: Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}