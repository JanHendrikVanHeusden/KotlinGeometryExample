package nl.jhvh.kotlin.geometry.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nl.jhvh.kotlin.geometry.model.twodimensional.TwoDimensional
import kotlin.time.ExperimentalTime

@ExperimentalTime
class TwoDimensionalService {

    fun <T : TwoDimensional> generateByExample(spec: TwoDimensionalSpecifier<T>, count: Int): Flow<T> = flow {
        for (i in 1..count) {
            emit(spec.generateWithDelay())
        }
    }
}