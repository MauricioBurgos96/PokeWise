package com.mauricioburgos.pokeyellow.core.platform
import com.mauricioburgos.pokeyellow.domain.ErrorResponse

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    class ServerError (val errorResponse: ErrorResponse) : Failure()
    object DatabaseError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}