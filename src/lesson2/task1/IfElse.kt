@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val i = age % 10

    return if (i == 0 || age in 11..19 || age % 100 in 11..19) "$age лет"
    else if (i == 1) "$age год"
    else if (i in 2..4) "$age года"
    else "$age лет"

}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = s1 + s2 + s3
    if (s1 + s2 < s / 2) return (s / 2 - s1 - s2) / v3 + t1 + t2
    else if (s1 < s / 2) return (s / 2 - s1) / v2 + t1
    else return (s / 2) / v1

}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    if (rookX1 != kingX && rookX2 != kingX && rookY1 != kingY && rookY2 != kingY) return 0
    else if ((rookX1 == kingX || rookY1 == kingY) && (rookX2 != kingX && rookY2 != kingY)) return 1
    else if ((rookX2 == kingX || rookY2 == kingY) && (rookX1 != kingX && rookY1 != kingY)) return 2
    else return 3
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    if (rookX != kingX && rookY != kingY && (abs(bishopX - kingX) != abs(bishopY - kingY))) return 0
    else if (rookX == kingX || rookY == kingY && (abs(bishopX - kingX) != abs(bishopY - kingY))) return 1
    else if (rookX != kingX && rookY != kingY && (abs(bishopX - kingX) == abs(bishopY - kingY))) return 2
    else {
        return 3
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var max1 = a
    var st2 = b
    var st3 = c
    if (b > a && b > c) {
        max1 = b
        st2 = a
        st3 = c
    }
    if (c > a && c > b) {
        max1 = c
        st2 = a
        st3 = b
    }
    when {
        sqr(max1) == sqr(st2) + sqr(st3) -> return 1
        max1 > st2 + st3 -> return -1
        sqr(max1) < sqr(st2) + sqr(st3) -> return 0
        // sqr(max1) > sqr(st2) + sqr(st3) -> return 2
        else -> return 2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if ((c > b && c > a) || (a > d && a > c)) return -1
    else if (c == b || (a == b && (c < a && b < d)) || (c == d && (a > c && d < b))) return 0
    else if (b > c && a < c && b < d) return b - c
    else if (a > c && a < d && b > d) return d - a
    else if (abs(a - b) > abs(c - d)) return abs(c - d)
    else return abs(a - b)
}
