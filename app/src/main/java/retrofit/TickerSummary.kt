package retrofit

data class TickerSummary(
    val change_percent : Double,
    val current_price : Double,
    val logo_url : String,
    val symbol : String
)
