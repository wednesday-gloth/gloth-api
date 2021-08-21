package com.glothapp.api.mapper

import com.glothapp.api.dbbean.IdSet
import com.glothapp.api.domainbean.*
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfDictionaryRecord
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.id.IdOfWord
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface DictionaryMapper {

    @Select(
        """
            INSERT INTO dictionary(profile_id, source_lang, target_lang)
            VALUES (#{profileId}, #{create.sourceLanguage}, #{create.targetLanguage})
            RETURNING id id, 
                      profile_id profileId, 
                      source_lang sourceLanguage, 
                      target_lang targetLanguage, 
                      startpoint startpoint
        """
    )
    fun createDictionary(
        @Param("profileId") profileId: IdOfProfile,
        @Param("create") dictionaryToCreate: DictionaryToCreate
    ): Dictionary

    @Select(
        """
            UPDATE dictionary
               SET source_lang = #{update.sourceLanguage},
                   target_lang = #{update.targetLanguage}
             WHERE id = #{update.id}
               AND profile_id = #{profileId}
            RETURNING id id,
                      profile_id profileId,
                      source_lang sourceLanguage,
                      target_lang targetLanguage,
                      startpoint startpoint        
        """
    )
    fun updateDictionary(
        @Param("profileId") profileId: IdOfProfile,
        @Param("update") dictionaryToUpdate: DictionaryToUpdate
    ): Dictionary?

    @Select(
        """
            INSERT INTO dictionary_record(dictionary_id)
            SELECT #{dictionaryId}
            WHERE EXISTS (
                SELECT d.id
                  FROM dictionary d
                 WHERE d.profile_id = #{profileId}
                   AND d.id = #{dictionaryId} 
                   AND d.endpoint = '3000-01-01Z'
            )
            RETURNING id id,
                      last_updated_date lastUpdatedDate
        """
    )
    fun createDictionaryRecord(
        @Param("profileId") profileId: IdOfProfile,
        @Param("dictionaryId") dictionaryId: IdOfDictionary
    ): DictionaryRecord?

    /* FIXME:
       I think we can just write
             WHERE id = (SELECT ... WHERE id = #{id})
       instead of
             WHERE id = #{id} AND EXISTS (SELECT ... WHERE id = #{id})
     */
    @Update(
        """
            UPDATE dictionary_record
               SET endpoint = now()
             WHERE id = #{recordId}
               AND EXISTS (
                SELECT dr.id
                  FROM dictionary_record dr
                  JOIN dictionary d ON d.id = dr.dictionary_id
                 WHERE d.profile_id = #{profileId}
                   AND d.endpoint = '3000-01-01Z'
                   AND dr.id = #{recordId}
                   AND dr.endpoint = '3000-01-01Z'
               )
        """
    )
    fun deleteDictionaryRecord(
        @Param("profileId") profileId: IdOfProfile,
        @Param("recordId") recordId: IdOfDictionaryRecord
    ): Int

    @Select(
        """
            INSERT INTO word(record_id, content, lang)
            SELECT #{create.recordId}, #{create.content}, #{create.languageDirection}::language_direction
            WHERE EXISTS (
                SELECT dr.id
                  FROM dictionary_record dr
                  JOIN dictionary d ON d.id = dr.dictionary_id
                 WHERE d.profile_id = #{profileId}
                   AND d.endpoint = '3000-01-01Z'
                   AND dr.id = #{create.recordId}
                   AND dr.endpoint = '3000-01-01Z'
            )
            RETURNING id id,
                      record_id recordId,
                      content "content",
                      lang languageDirection,
                      startpoint startpoint
        """
    )
    fun createWord(@Param("profileId") profileId: IdOfProfile, @Param("create") wordToCreate: WordToCreate): Word?

    @Select(
        """
            UPDATE word
               SET content = #{update.content}
             WHERE id = #{update.id}
               AND EXISTS (
                SELECT w.id
                  FROM word w
                  JOIN dictionary_record dr ON dr.id = w.record_id
                  JOIN dictionary d ON d.id = dr.dictionary_id
                 WHERE d.profile_id = #{profileId}
                   AND d.endpoint = '3000-01-01Z'
                   AND dr.endpoint = '3000-01-01Z'
                   AND w.id = #{update.id}
                   AND w.endpoint = '3000-01-01Z'
            )
            RETURNING id id,
                      record_id recordId,
                      content "content",
                      lang languageDirection,
                      startpoint startpoint
        """
    )
    fun updateWord(@Param("profileId") profileId: IdOfProfile, @Param("update") wordToUpdate: WordToUpdate): Word?

    @Update(
        """
            UPDATE word
               SET endpoint = now()
             WHERE id = #{wordId}
               AND EXISTS (
                SELECT w.id
                  FROM word w
                  JOIN dictionary_record dr ON dr.id = w.record_id
                  JOIN dictionary d ON d.id = dr.dictionary_id
                 WHERE d.profile_id = #{profileId}
                   AND d.endpoint = '3000-01-01Z'
                   AND dr.endpoint = '3000-01-01Z'
                   AND w.id = #{wordId}
                   AND w.endpoint = '3000-01-01Z'
               )
        """
    )
    fun deleteWord(@Param("profileId") profileId: IdOfProfile, @Param("wordId") wordId: IdOfWord): Int

    @Update(
        """
            UPDATE word
               SET endpoint = now()
             WHERE id = ANY(
              SELECT w.id
                FROM word w
                JOIN dictionary_record dr ON dr.id = w.record_id
                JOIN dictionary d ON d.id = dr.dictionary_id
               WHERE d.profile_id = #{profileId}
                 AND d.endpoint = '3000-01-01Z'
                 AND dr.id = #{recordId}
                 AND dr.endpoint = '3000-01-01Z'
                 AND w.endpoint = '3000-01-01Z'
             )
        """
    )
    fun deleteWordsByRecordId(
        @Param("profileId") profileId: IdOfProfile,
        @Param("recordId") recordId: IdOfDictionaryRecord
    )

    @Select(
        """
            SELECT d.id id,
                   d.profile_id profileId,
                   d.source_lang sourceLanguage,
                   d.target_lang targetLanguage,
                   d.startpoint startpoint
              FROM dictionary d
             WHERE d.profile_id = #{profileId}
               AND d.id = #{dictionaryId}
               AND d.endpoint = '3000-01-01Z'
        """
    )
    fun findDictionaryById(
        @Param("profileId") profileId: IdOfProfile,
        @Param("dictionaryId") dictionaryId: IdOfDictionary
    ): Dictionary?

    @Select(
        """
            SELECT d.id id,
                   d.profile_id profileId,
                   d.source_lang sourceLanguage,
                   d.target_lang targetLanguage,
                   d.startpoint startpoint
              FROM dictionary d
             WHERE d.profile_id = #{profileId}
               AND d.endpoint = '3000-01-01Z'
        """
    )
    fun listDictionaries(@Param("profileId") profileId: IdOfProfile): List<Dictionary>

    @Select(
        """
        SELECT dr.id id,
               dr.last_updated_date lastUpdatedDate
          FROM dictionary_record dr
          JOIN dictionary d ON d.id = dr.dictionary_id
         WHERE dr.dictionary_id = #{dictionaryId}
           AND dr.endpoint = '3000-01-01Z'
           AND d.profile_id = #{profileId}
           AND d.endpoint = '3000-01-01Z'
        """
    )
    fun listDictionaryRecords(
        @Param("profileId") profileId: IdOfProfile,
        @Param("dictionaryId") dictionaryId: IdOfDictionary
    ): List<DictionaryRecord>

    @Select(
        """
            SELECT w.id id,
                   w.record_id recordId,
                   w.content "content",
                   w.lang languageDirection,
                   w.startpoint startpoint
              FROM word w
             WHERE w.record_id = ANY(#{recordIds})
               AND w.endpoint = '3000-01-01Z'
        """
    )
    fun listWordsByRecordIds(@Param("recordIds") recordIds: IdSet<IdOfDictionaryRecord>): List<Word>
}
