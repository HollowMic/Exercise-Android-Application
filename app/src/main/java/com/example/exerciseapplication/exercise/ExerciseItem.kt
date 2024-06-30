package com.example.exerciseapplication.exercise

class ExerciseItem(
    private var name: String = "Weight",
    private var weight: Float = 0.0f,
    private var numOfReps: Int = 3
) {

    fun getName(): String {
        return name
    }

    fun setName(newName: String) {
        name = newName
    }

    fun getWeight(): Float {
        return weight
    }
    fun addWeight() {
        weight += 2.5f
    }

    fun subtractWeight() {
        weight -= 2.5f
        if (weight < 0.0f) {
            weight = 0.0f
        }
    }

    fun getNumOfReps(): Int {
        return numOfReps
    }

    fun addReps() {
        numOfReps += 1
    }

    fun subtractNumOfReps() {
        numOfReps -= 1
        if (numOfReps < 0) {
            numOfReps = 0
        }
    }
}