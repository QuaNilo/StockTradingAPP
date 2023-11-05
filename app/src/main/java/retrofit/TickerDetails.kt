package retrofit

data class TickerDetails(
    val CEO: String,
    val chart_data: ChartData,
    val description: String,
    val details: TickerDetails_details,
    val logo_url: String,
    val sector: String,
    val symbol: String
)
