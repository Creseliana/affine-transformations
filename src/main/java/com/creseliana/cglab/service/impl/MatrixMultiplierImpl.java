package com.creseliana.cglab.service.impl;

import com.creseliana.cglab.service.MatrixMultiplier;

public class MatrixMultiplierImpl implements MatrixMultiplier {

    @Override
    public double[][] multiply(double[][] firstMultiplier, double[][] secondMultiplier) {
        int firstRowSize = firstMultiplier.length;
        int firstColumnSize = firstMultiplier[0].length;
        int secondRowSize = secondMultiplier.length;
        int secondColumnSize = secondMultiplier[0].length;

        if (firstColumnSize != secondRowSize) {
            return null;
        }

        double[][] result = new double[firstRowSize][secondColumnSize];

        for (int i = 0; i < firstRowSize; i++) {
            for (int j = 0; j < secondColumnSize; j++) {
                for (int k = 0; k < secondRowSize; k++) {

                    result[i][j] += firstMultiplier[i][k] * secondMultiplier[k][j];
                }
            }
        }
        return result;
    }


}
