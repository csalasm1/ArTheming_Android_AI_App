package com.example.trialcomposearttheming.ui.components

import android.util.Log

private fun kMeans(
    elements: Array<Int>,
    numIterations: Int,
    numClusters: Int
): MutableList<MutableList<Int>> {

    // Setup: create K clusters with random elements from input
    val clusters = mutableListOf<MutableList<Int>>()
    val centroids = Array(numClusters) { 0 }
    for (i in 0 until numClusters) {
        clusters.add(mutableListOf(elements[i]))
    }

    // Algorithm
    for (i in 0 until numIterations) {
        Log.d("LOG", "Iteration $i")
        for (j in 0 until numClusters) {
            // Find centroids
            centroids[j] = findCentroid(clusters[j], centroids[j])
            Log.d("LOG", "Centroid $j: ${centroids[j]}")
        }
        clusters.forEach{ list ->
            list.removeAll {
                true
            }
        }
        for (element in elements) {
            // Assign elements to the best cluster
            val index = assignToCluster(element, centroids)
            clusters[index].add(element)
            Log.d("LOG", "Element: $element assigned to the $index cluster")
        }

    }

    return clusters
}

// Find the centroid of a cluster
fun findCentroid(cluster: MutableList<Int>?, actualCentroid: Int): Int {
    var newCentroid = 0
    cluster?.forEach {
        newCentroid = newCentroid.plus(it)
    }
    return newCentroid / cluster!!.size
}

// Returns the index of the cluster that this element must be assigned to
fun assignToCluster(element: Int, centroids: Array<Int>): Int {
    var minValue = 0
    var minIndex = 0
    for (i in centroids.indices) {
        if (kotlin.math.abs(element - centroids[i]) < kotlin.math.abs(element - minValue)) {
            minIndex = i
            minValue = centroids[i]
        }
    }
    // Centroids and clusters are in sync
    return minIndex
}