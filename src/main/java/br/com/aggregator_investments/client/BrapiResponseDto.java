package br.com.aggregator_investments.client;

import java.util.LinkedList;
import java.util.List;

public record BrapiResponseDto(LinkedList<StockDto> results) {

}
