package com.minek.kotlin.everywehre.kelibs.flatdb

interface Row<out T> {
    val pk: T
}

fun <T, R : Row<T>> Collection<R>.find(pk: T): R? = firstOrNull { it.pk == pk }

fun <T, R : Row<T>> Collection<R>.replace(row: R): List<R> = map { if (it.pk == row.pk) row else it }

fun <T, R : Row<T>> Collection<R>.replaceOrAdd(row: R, orAdd: Boolean): List<R> = if (orAdd) plus(row) else replace(row)
