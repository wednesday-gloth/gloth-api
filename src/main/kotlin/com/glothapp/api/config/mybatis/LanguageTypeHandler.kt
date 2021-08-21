package com.glothapp.api.config.mybatis

import com.glothapp.api.domainbean.LanguageCode
import org.apache.ibatis.type.*
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(LanguageCode::class)
class LanguageTypeHandler : BaseTypeHandler<LanguageCode>() {

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: LanguageCode, jdbcType: JdbcType?) {
        ps.setString(i, parameter.code)
    }

    override fun getNullableResult(rs: ResultSet, columnName: String): LanguageCode {
        return LanguageCode.from(rs.getString(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): LanguageCode {
        return LanguageCode.from(rs.getString(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): LanguageCode {
        return LanguageCode.from(cs.getString(columnIndex))
    }
}
