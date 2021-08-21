package com.glothapp.api.config.mybatis

import com.glothapp.api.id.IdOfWord
import org.apache.ibatis.type.*
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

@MappedJdbcTypes(JdbcType.BIGINT)
@MappedTypes(IdOfWord::class)
class IdOfWordTypeHandler : BaseTypeHandler<IdOfWord>() {

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: IdOfWord, jdbcType: JdbcType?) {
        ps.setInt(i, parameter.id)
    }

    override fun getNullableResult(rs: ResultSet, columnName: String): IdOfWord {
        return IdOfWord(rs.getInt(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): IdOfWord {
        return IdOfWord(rs.getInt(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): IdOfWord {
        return IdOfWord(cs.getInt(columnIndex))
    }
}
