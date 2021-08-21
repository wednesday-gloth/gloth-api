package com.glothapp.api.config.mybatis

import com.glothapp.api.id.IdOfProfile
import org.apache.ibatis.type.*
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

@MappedJdbcTypes(JdbcType.BIGINT)
@MappedTypes(IdOfProfile::class)
class IdOfProfileTypeHandler : BaseTypeHandler<IdOfProfile>() {

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: IdOfProfile, jdbcType: JdbcType?) {
        ps.setInt(i, parameter.id)
    }

    override fun getNullableResult(rs: ResultSet, columnName: String): IdOfProfile {
        return IdOfProfile(rs.getInt(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): IdOfProfile {
        return IdOfProfile(rs.getInt(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): IdOfProfile {
        return IdOfProfile(cs.getInt(columnIndex))
    }
}
