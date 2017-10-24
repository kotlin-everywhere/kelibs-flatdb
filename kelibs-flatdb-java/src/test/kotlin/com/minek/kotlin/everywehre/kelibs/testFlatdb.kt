package com.minek.kotlin.everywehre.kelibs

import com.minek.kotlin.everywehre.kelibs.flatdb.Row
import com.minek.kotlin.everywehre.kelibs.flatdb.find
import com.minek.kotlin.everywehre.kelibs.flatdb.replace
import com.minek.kotlin.everywehre.kelibs.flatdb.replaceOrAdd
import org.junit.Assert.assertEquals
import org.junit.Test

class TestFlatdb {
    @Test
    fun testFind() {
        val li = (0..9).map(::SimpleRow)
        assertEquals(li[0], li.find(0))
        assertEquals(li[3], li.find(3))
        assertEquals(li[9], li.find(9))
        assertEquals(null, li.find(10))
    }

    @Test
    fun testReplace() {
        val li = ('a'..'c').map { StringRow("$it", "$it") }
        val (a, b, c) = li

        val a1 = StringRow("a", "modified")
        val b1 = StringRow("b", "modified")
        val c1 = StringRow("c", "modified")
        val d = StringRow("d", "modified")

        assertEquals(listOf(a1, b, c), li.replace(a1))
        assertEquals(listOf(a, b1, c), li.replace(b1))
        assertEquals(listOf(a, b, c1), li.replace(c1))
        assertEquals(listOf(a, b, c), li.replace(d))
        assertEquals(listOf(a, b, c, d), li.replaceOrAdd(d, true))
        assertEquals(listOf(a, b, c), li.replaceOrAdd(d, false))
    }

    data class SimpleRow<out T>(override val pk: T) : Row<T>
    data class StringRow(override val pk: String, val value: String) : Row<String>
}