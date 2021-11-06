package com.glothapp.api.config.mybatis

import com.glothapp.api.domainbean.LanguageDirection
import org.apache.ibatis.type.*
import java.sql.*

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(LanguageDirection::class)
class LanguageDirectionTypeHandler : BaseTypeHandler<LanguageDirection>() {

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: LanguageDirection, jdbcType: JdbcType?) {
        ps.setString(i, parameter.code)
    }

    override fun getNullableResult(rs: ResultSet, columnName: String): LanguageDirection {
        return LanguageDirection.from(rs.getString(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): LanguageDirection {
        return LanguageDirection.from(rs.getString(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): LanguageDirection {
        return LanguageDirection.from(cs.getString(columnIndex))
    }
}
