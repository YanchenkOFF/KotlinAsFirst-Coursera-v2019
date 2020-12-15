@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map({ it * it })

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */

fun abs(v: List<Double>): Double {
    var rez = 0.0
    if (v.isEmpty()) return rez
    else {
        for (i in v.indices) {
            rez += v[i] * v[i]
        }
    }
    return sqrt(rez)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    var rez = list.sum() / list.size
    return rez
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    var sra = mean(list)
    for (i in 0 until list.size) list[i] -= sra
    return list
}


/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    if (a.isEmpty() && b.isEmpty()) return 0
    for (i in a.indices) result += a[i] * b[i]
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0.0
    if (p.isEmpty()) return 0
    for (i in p.indices) result += p[i] * x.toDouble().pow(i.toDouble())
    return result.toInt()
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isEmpty()) return list
    var accum = 0
    for (i in list.indices) {
        list[i] = list[i] + accum
        accum = list[i]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var t = n
    var list = listOf<Int>()
    while (t > 1) {
        for (i in 2..t) {
            if (t % i == 0) {
                list += i
                t /= i
                break
            }
        }
    }
    return list.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var division = n
    val listOfNumberInBase = mutableListOf<Int>()
    while (division > 0) {
        listOfNumberInBase += division % base
        division /= base
    }
    return listOfNumberInBase.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var result = ""
    val baseChars = ('0'..'9').toList() + ('a'..'z').toList()
    val indexList = convert(n, base)
    indexList.forEach { result += baseChars[it] }
    return result
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    val digits = digits.reversed()
    for (i in digits.indices) {
        print("index=$i element=${digits[i]} ;")
        result += (digits[i] * base.toDouble().pow(i)).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val baseChar = ('0'..'9').toList() + ('a'..'z').toList()
    var numberList = str.toList().reversed()
    var result = 0.0
    for (i in numberList.indices) {
        result += baseChar.indexOf(numberList[i]) * (base.toDouble().pow(i.toDouble()))
    }
    return result.toInt()
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val baseTens = listOf<String>("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val baseOne = listOf<String>("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    val baseHundreds = listOf<String>("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val baseThousands = listOf<String>("", "M", "MM", "MMM")
    var result = ""
    var countBase = 0
    var number = n
    while (number > 0) {
        when {
            countBase == 0 -> result = baseOne[number % 10] + result
            countBase == 1 -> result = baseTens[number % 10] + result
            countBase == 2 -> result = baseHundreds[number % 10] + result
            countBase == 3 -> result = baseThousands[number % 10] + result
        }
        number /= 10
        countBase++
    }
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val baseOnes = listOf<String>("", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val base11 = listOf<String>(
        "",
        "одинадцать",
        "двенадцать",
        "тринадцать",
        "четырнадцать",
        "пятнадцать",
        "шестнадцать",
        "семнадцать",
        "восемнадцать",
        "девятнадцать"
    )
    val baseTens = listOf<String>(
        "",
        "десять",
        "двадцать",
        "тридцать",
        "сорок",
        "пятьдесят",
        "шестьдесят",
        "семьдесят",
        "восемьдесят",
        "девяносто"
    )
    val baseHundreds = listOf<String>(
        "",
        "сто",
        "двести",
        "триста",
        "четыреста",
        "пятьсот",
        "шестьсот",
        "семьсот",
        "восемьсот",
        "девятьсот"
    )
    val baseThausends = listOf<String>(
        "",
        "одна тысяча",
        "две тысячи",
        "три тысячи",
        "четыре тысячи",
        "пять тысяч",
        "шесть тысяч",
        "семь тысяч",
        "восемь тысяч",
        "девять тысяч"
    )
    var number = n
    var result = mutableListOf<String>()
    var numberList = listOf<Int>()
    while (number > 0) {
        numberList += number % 10
        number /= 10
    }
    var count = 0
    for (i in numberList.indices) {

        when {
            i == 0 -> result.add(i, baseOnes[numberList[i]])
            i == 1 && numberList[i] != 1 -> result.add(i, baseTens[numberList[i]])
            i == 1 && numberList[i] == 1 -> {
                result.add(i, base11[numberList[i - 1]])
                result[i - 1] = ""
            }
            i == 2 -> result.add(i, baseHundreds[numberList[i]])
            i == 3 -> result.add(i, baseThausends[numberList[i]])
            i == 4 && numberList[i] != 1 -> result.add(i, baseTens[numberList[i]])
            i == 4 && numberList[i] == 1 -> {
                result.add(i, base11[numberList[i - 1]] + " тысяч")
                result[i - 1] = ""
            }

            i == 5 && numberList[i - 2] == 0 -> result.add(i, baseHundreds[numberList[i]] + " тысяч")
            i == 5 -> result.add(i, baseHundreds[numberList[i]])

        }
    }
    result.removeAll(listOf(""))
    return result.reversed().joinToString(separator = " ")
}
