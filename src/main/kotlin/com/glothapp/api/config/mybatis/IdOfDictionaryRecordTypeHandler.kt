package com.glothapp.api.config.mybatis

import com.glothapp.api.id.IdOfDictionaryRecord
import org.apache.ibatis.type.*
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

// TODO: create common TypeHandler for all ids
@MappedJdbcTypes(JdbcType.BIGINT)
@MappedTypes(IdOfDictionaryRecord::class)
class IdOfDictionaryRecordTypeHandler : BaseTypeHandler<IdOfDictionaryRecord>() {
    
    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: IdOfDictionaryRecord, jdbcType: JdbcType?) {
        ps.setInt(i, parameter.id)
    }

    override fun getNullableResult(rs: ResultSet, columnName: String): IdOfDictionaryRecord {
        return IdOfDictionaryRecord(rs.getInt(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): IdOfDictionaryRecord {
        return IdOfDictionaryRecord(rs.getInt(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): IdOfDictionaryRecord {
        return IdOfDictionaryRecord(cs.getInt(columnIndex))
    }
}
