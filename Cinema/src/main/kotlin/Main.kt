package cinema

fun printCinema(cinema: Array<IntArray>) {
    println("Cinema:")
    var rowLine: String
    for (row in cinema.indices) {
        rowLine = if (row == 0) "  " else " $row"
        for (column in cinema[row].indices) {
            if (column == 0) continue
            rowLine += if (row == 0) " $column" else " "+cinema[row][column].toChar()
        }
        println(rowLine)
    }
}

fun getSeatPrice(cinema: Array<IntArray>, ticketRow: Int): Int {
    return if ((cinema.size-1)*(cinema[0].size-1) > 60 && ticketRow > (cinema.size-1)/2 ) 8 else 10
}

fun buyTicket(cinema: Array<IntArray>) {
    do {
        println("Enter a row number:")
        val ticketRow = readln().toInt()
        println("Enter a seat number in that row:")
        val ticketSeatInRow = readln().toInt()
        if (!(ticketRow in 1..cinema.lastIndex && ticketSeatInRow in 1..cinema[0].lastIndex)) {
            println("Wrong input!")
            continue
        }
        if (cinema[ticketRow][ticketSeatInRow] == 'B'.code) {
            println("That ticket has already been purchased")
            continue
        }
        println("Ticket price: $"+getSeatPrice(cinema,ticketRow))
        cinema[ticketRow][ticketSeatInRow] = 'B'.code
        break
    } while (true)
}

fun statisticsCinema(cinema: Array<IntArray>) {
    var soldCountOfTickets = 0
    var sumSoldTickets = 0
    for (row in cinema.indices) {
        for (column in cinema[row].indices) {
            if (cinema[row][column] == 'B'.code) {
                soldCountOfTickets += 1
                sumSoldTickets += getSeatPrice(cinema,row)
            }
        }
    }
    val percentage: Double = 100.0*soldCountOfTickets/((cinema.size-1)*(cinema[0].size -1))
    val formatPercentage = "%.2f".format(percentage)
    println("Number of purchased tickets: $soldCountOfTickets")
    println("Percentage: $formatPercentage%")
    println("Current income: $$sumSoldTickets")
    println("Total income: $"+(if ((cinema.size-1)*(cinema[0].size -1) > 60) (cinema.size-1)/2*10 + ((cinema.size-1) -(cinema.size-1)/2)*8 else (cinema.size-1)*10)*(cinema[0].size -1))
}

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRow = readln().toInt()
    val cinema: Array<IntArray> = Array(rows+1) { IntArray(seatsInRow+1) {'S'.code} }
    if (cinema.isEmpty()) return
    while (true) {
        println("""1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit""")
        when(readln().toInt()) {
            1 -> printCinema(cinema)
            2 -> buyTicket(cinema)
            3 -> statisticsCinema(cinema)
            else -> return
        }
    }
}