package com.glothapp.api.config.mybatis

import com.glothapp.api.id.IdOfDictionary
import org.apache.ibatis.type.*
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

@MappedJdbcTypes(JdbcType.BIGINT)
@MappedTypes(IdOfDictionary::class)
class IdOfDictionaryTypeHandler : BaseTypeHandler<IdOfDictionary>() {

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: IdOfDictionary, jdbcType: JdbcType?) {
        ps.setInt(i, parameter.id)
    }

    override fun getNullableResult(rs: ResultSet, columnName: String): IdOfDictionary {
        return IdOfDictionary(rs.getInt(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): IdOfDictionary {
        return IdOfDictionary(rs.getInt(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): IdOfDictionary {
        return IdOfDictionary(cs.getInt(columnIndex))
    }
}
