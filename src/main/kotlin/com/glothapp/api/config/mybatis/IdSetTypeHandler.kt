package com.glothapp.api.config.mybatis

import com.glothapp.api.dbbean.IdSet
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedJdbcTypes
import org.apache.ibatis.type.MappedTypes
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.system.exitProcess

@MappedJdbcTypes(JdbcType.ARRAY)
@MappedTypes(IdSet::class)
class IdSetTypeHandler : BaseTypeHandler<IdSet<*>>() {

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: IdSet<*>, jdbcType: JdbcType?) {
        ps.setArray(i, ps.connection.createArrayOf("bigint", parameter.rawIds))
    }

    override fun getNullableResult(rs: ResultSet?, columnName: String?): IdSet<*> {
        fail()
    }

    override fun getNullableResult(rs: ResultSet?, columnIndex: Int): IdSet<*> {
        fail()
    }

    override fun getNullableResult(cs: CallableStatement?, columnIndex: Int): IdSet<*> {
        fail()
    }

    private fun fail(): Nothing {
        throw IllegalStateException("IdSet<*> is designed to be a mapper parameter only. It should not be queries from DB")
    }
}
