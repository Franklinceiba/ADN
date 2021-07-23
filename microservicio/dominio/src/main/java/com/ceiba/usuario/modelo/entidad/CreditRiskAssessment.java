package com.ceiba.usuario.modelo.entidad;

public class CreditRiskAssessment {

	/**
	 * Metodo que devuelve la desviacion estandar poblacional para el arreglo de
	 * pagos tardios por dias
	 * 
	 * @param paymentDelays El arreglo de los pagos tardios inusuales, con los
	 *                      numeros de dias de retraso en los pagos
	 * @return La desviacion estandar poblacional
	 */
	public double standardDeviation(int[] paymentDelays) {
		int lengthVector = paymentDelays.length;
		int summation = 0;
		double average = 0;
		double variance = 0.0;
		double standardDeviation;
		for (int i = 0; i < paymentDelays.length; i++) {
			summation += paymentDelays[i];
		}

		average = summation / (lengthVector * 1.0);

		for (int i = 0; i < lengthVector; i++) {
			double rango;

			rango = Math.pow(paymentDelays[i] - average, 2f);

			variance += rango;
		}

		variance = variance / (double) (lengthVector);

		standardDeviation = Math.sqrt(variance);

		return standardDeviation;
	}// Cierre del metodo desviacion estandar

	/**
	 * Metodo que devuelve el indice mas alto de la anomalia con el pico mas alto en
	 * el arreglo de los pagos tardios inusuales
	 * 
	 * @param paymentDelays El arreglo de los pagos tardios inusuales, con los
	 *                      numeros de dias de retraso en los pagos
	 * @return El indice de la anomalia con el pico mas alto en el arreglo
	 */
	public int paymentDelayMaxPeakIndex(int[] paymentDelays) {
		int lengthVector = paymentDelays.length;
		int lengthVectorAntepenultimate = paymentDelays.length - 1;
		int startVector = 0;
		int index = -1;
		int previousDifference = 0;
		int differenceAfter = 0;

		for (int i = 0; i < lengthVector; i++) {
			if (i == startVector && paymentDelays[i] > paymentDelays[i + 1]) {
				index = 0;
				differenceAfter = paymentDelays[i] - paymentDelays[i + 1];
			}
			if (i == lengthVectorAntepenultimate && paymentDelays[i] > paymentDelays[i - 1]
					&& (paymentDelays[i] - paymentDelays[i - 1]) > previousDifference) {
				return i;
			}
			if (i != startVector && i != lengthVectorAntepenultimate) {
				index = this.validationVector(index, i, paymentDelays[i], paymentDelays[i - 1], paymentDelays[i + 1],
						previousDifference, differenceAfter);
			}
		}
		return index;
	}
	
	public int validationVector(int index, int position, int presentValue, int previousValue, int valueAfter, int previousDifference, int differenceAfter) {
		if ((presentValue - previousValue) > previousDifference
				&& (presentValue - valueAfter) > differenceAfter
				&& presentValue > previousValue && presentValue > valueAfter) {
			return position;
		}
		
		return index;
	}

	/**
	 * Metodo que devuelve la propabilidad de pagos tardios en un arreglo donde cada
	 * posicion representa la probabilidad de pago tardio para determinado periodo
	 * de tiempo de los diferentes productos
	 * 
	 * @param paymentDelays Un arreglo de varias dimensiones donde las filas son
	 *                      cantidad de productos y las columnas es el periodo de
	 *                      tiempo
	 * @return Un arreglo de las Probabilidad de pago tardío por período
	 */
	public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
		int columns = paymentDelays.length;
		int rows = paymentDelays[0].length;
		double summation = 1 / (double) columns;
		double average = 0.0;
		double[] vectorEnd = new double[rows];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (paymentDelays[j][i] > 0) {
					average += summation;
				}

			}
			vectorEnd[i] = average;
			average = 0.0;
		}
		return vectorEnd;
	}// Cierre del método Probabilidad de pago tardío por período
}
