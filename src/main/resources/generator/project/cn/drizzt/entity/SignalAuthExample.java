package cn.drizzt.entity;

import java.util.ArrayList;
import java.util.List;

public class SignalAuthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private int pageIndex;

    private int pageSize;

    public SignalAuthExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex=pageIndex;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize=pageSize;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public SignalAuthExample(int pageIndex, int pageSize) {
        this();
        this.pageIndex=pageIndex;
        this.pageSize=pageSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNull() {
            addCriterion("batch_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(String value) {
            addCriterion("batch_id =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(String value) {
            addCriterion("batch_id <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(String value) {
            addCriterion("batch_id >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("batch_id >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(String value) {
            addCriterion("batch_id <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(String value) {
            addCriterion("batch_id <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLike(String value) {
            addCriterion("batch_id like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotLike(String value) {
            addCriterion("batch_id not like", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<String> values) {
            addCriterion("batch_id in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<String> values) {
            addCriterion("batch_id not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(String value1, String value2) {
            addCriterion("batch_id between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(String value1, String value2) {
            addCriterion("batch_id not between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andChIsNull() {
            addCriterion("ch is null");
            return (Criteria) this;
        }

        public Criteria andChIsNotNull() {
            addCriterion("ch is not null");
            return (Criteria) this;
        }

        public Criteria andChEqualTo(Integer value) {
            addCriterion("ch =", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChNotEqualTo(Integer value) {
            addCriterion("ch <>", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChGreaterThan(Integer value) {
            addCriterion("ch >", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChGreaterThanOrEqualTo(Integer value) {
            addCriterion("ch >=", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChLessThan(Integer value) {
            addCriterion("ch <", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChLessThanOrEqualTo(Integer value) {
            addCriterion("ch <=", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChIn(List<Integer> values) {
            addCriterion("ch in", values, "ch");
            return (Criteria) this;
        }

        public Criteria andChNotIn(List<Integer> values) {
            addCriterion("ch not in", values, "ch");
            return (Criteria) this;
        }

        public Criteria andChBetween(Integer value1, Integer value2) {
            addCriterion("ch between", value1, value2, "ch");
            return (Criteria) this;
        }

        public Criteria andChNotBetween(Integer value1, Integer value2) {
            addCriterion("ch not between", value1, value2, "ch");
            return (Criteria) this;
        }

        public Criteria andCalledIsNull() {
            addCriterion("called is null");
            return (Criteria) this;
        }

        public Criteria andCalledIsNotNull() {
            addCriterion("called is not null");
            return (Criteria) this;
        }

        public Criteria andCalledEqualTo(String value) {
            addCriterion("called =", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotEqualTo(String value) {
            addCriterion("called <>", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledGreaterThan(String value) {
            addCriterion("called >", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledGreaterThanOrEqualTo(String value) {
            addCriterion("called >=", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledLessThan(String value) {
            addCriterion("called <", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledLessThanOrEqualTo(String value) {
            addCriterion("called <=", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledLike(String value) {
            addCriterion("called like", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotLike(String value) {
            addCriterion("called not like", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledIn(List<String> values) {
            addCriterion("called in", values, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotIn(List<String> values) {
            addCriterion("called not in", values, "called");
            return (Criteria) this;
        }

        public Criteria andCalledBetween(String value1, String value2) {
            addCriterion("called between", value1, value2, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotBetween(String value1, String value2) {
            addCriterion("called not between", value1, value2, "called");
            return (Criteria) this;
        }

        public Criteria andCallingIsNull() {
            addCriterion("calling is null");
            return (Criteria) this;
        }

        public Criteria andCallingIsNotNull() {
            addCriterion("calling is not null");
            return (Criteria) this;
        }

        public Criteria andCallingEqualTo(String value) {
            addCriterion("calling =", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingNotEqualTo(String value) {
            addCriterion("calling <>", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingGreaterThan(String value) {
            addCriterion("calling >", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingGreaterThanOrEqualTo(String value) {
            addCriterion("calling >=", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingLessThan(String value) {
            addCriterion("calling <", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingLessThanOrEqualTo(String value) {
            addCriterion("calling <=", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingLike(String value) {
            addCriterion("calling like", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingNotLike(String value) {
            addCriterion("calling not like", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingIn(List<String> values) {
            addCriterion("calling in", values, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingNotIn(List<String> values) {
            addCriterion("calling not in", values, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingBetween(String value1, String value2) {
            addCriterion("calling between", value1, value2, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingNotBetween(String value1, String value2) {
            addCriterion("calling not between", value1, value2, "calling");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Long value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Long value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Long value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Long value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Long value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Long value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Long> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Long> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Long value1, Long value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Long value1, Long value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationIsNull() {
            addCriterion("voice_duration is null");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationIsNotNull() {
            addCriterion("voice_duration is not null");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationEqualTo(Long value) {
            addCriterion("voice_duration =", value, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationNotEqualTo(Long value) {
            addCriterion("voice_duration <>", value, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationGreaterThan(Long value) {
            addCriterion("voice_duration >", value, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationGreaterThanOrEqualTo(Long value) {
            addCriterion("voice_duration >=", value, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationLessThan(Long value) {
            addCriterion("voice_duration <", value, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationLessThanOrEqualTo(Long value) {
            addCriterion("voice_duration <=", value, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationIn(List<Long> values) {
            addCriterion("voice_duration in", values, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationNotIn(List<Long> values) {
            addCriterion("voice_duration not in", values, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationBetween(Long value1, Long value2) {
            addCriterion("voice_duration between", value1, value2, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andVoiceDurationNotBetween(Long value1, Long value2) {
            addCriterion("voice_duration not between", value1, value2, "voiceDuration");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Long value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Long value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Long value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Long value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Long value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Long> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Long> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Long value1, Long value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Long value1, Long value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurIsNull() {
            addCriterion("start_record_dur is null");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurIsNotNull() {
            addCriterion("start_record_dur is not null");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurEqualTo(Long value) {
            addCriterion("start_record_dur =", value, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurNotEqualTo(Long value) {
            addCriterion("start_record_dur <>", value, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurGreaterThan(Long value) {
            addCriterion("start_record_dur >", value, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurGreaterThanOrEqualTo(Long value) {
            addCriterion("start_record_dur >=", value, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurLessThan(Long value) {
            addCriterion("start_record_dur <", value, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurLessThanOrEqualTo(Long value) {
            addCriterion("start_record_dur <=", value, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurIn(List<Long> values) {
            addCriterion("start_record_dur in", values, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurNotIn(List<Long> values) {
            addCriterion("start_record_dur not in", values, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurBetween(Long value1, Long value2) {
            addCriterion("start_record_dur between", value1, value2, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andStartRecordDurNotBetween(Long value1, Long value2) {
            addCriterion("start_record_dur not between", value1, value2, "startRecordDur");
            return (Criteria) this;
        }

        public Criteria andTranslationIsNull() {
            addCriterion("translation is null");
            return (Criteria) this;
        }

        public Criteria andTranslationIsNotNull() {
            addCriterion("translation is not null");
            return (Criteria) this;
        }

        public Criteria andTranslationEqualTo(String value) {
            addCriterion("translation =", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationNotEqualTo(String value) {
            addCriterion("translation <>", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationGreaterThan(String value) {
            addCriterion("translation >", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationGreaterThanOrEqualTo(String value) {
            addCriterion("translation >=", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationLessThan(String value) {
            addCriterion("translation <", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationLessThanOrEqualTo(String value) {
            addCriterion("translation <=", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationLike(String value) {
            addCriterion("translation like", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationNotLike(String value) {
            addCriterion("translation not like", value, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationIn(List<String> values) {
            addCriterion("translation in", values, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationNotIn(List<String> values) {
            addCriterion("translation not in", values, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationBetween(String value1, String value2) {
            addCriterion("translation between", value1, value2, "translation");
            return (Criteria) this;
        }

        public Criteria andTranslationNotBetween(String value1, String value2) {
            addCriterion("translation not between", value1, value2, "translation");
            return (Criteria) this;
        }

        public Criteria andAutoDialIsNull() {
            addCriterion("auto_dial is null");
            return (Criteria) this;
        }

        public Criteria andAutoDialIsNotNull() {
            addCriterion("auto_dial is not null");
            return (Criteria) this;
        }

        public Criteria andAutoDialEqualTo(Integer value) {
            addCriterion("auto_dial =", value, "autoDial");
            return (Criteria) this;
        }

        public Criteria andAutoDialNotEqualTo(Integer value) {
            addCriterion("auto_dial <>", value, "autoDial");
            return (Criteria) this;
        }

        public Criteria andAutoDialGreaterThan(Integer value) {
            addCriterion("auto_dial >", value, "autoDial");
            return (Criteria) this;
        }

        public Criteria andAutoDialGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_dial >=", value, "autoDial");
            return (Criteria) this;
        }

        public Criteria andAutoDialLessThan(Integer value) {
            addCriterion("auto_dial <", value, "autoDial");
            return (Criteria) this;
        }

        public Criteria andAutoDialLessThanOrEqualTo(Integer value) {
            addCriterion("auto_dial <=", value, "autoDial");
            return (Criteria) this;
        }

        public Criteria andAutoDialIn(List<Integer> values) {
            addCriterion("auto_dial in", values, "autoDial");
            return (Criteria) this;
        }

        public Criteria andAutoDialNotIn(List<Integer> values) {
            addCriterion("auto_dial not in", values, "autoDial");
            return (Criteria) this;
        }

        public Criteria andAutoDialBetween(Integer value1, Integer value2) {
            addCriterion("auto_dial between", value1, value2, "autoDial");
            return (Criteria) this;
        }

        public Criteria andAutoDialNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_dial not between", value1, value2, "autoDial");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeIsNull() {
            addCriterion("tone_analyze is null");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeIsNotNull() {
            addCriterion("tone_analyze is not null");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeEqualTo(Integer value) {
            addCriterion("tone_analyze =", value, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeNotEqualTo(Integer value) {
            addCriterion("tone_analyze <>", value, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeGreaterThan(Integer value) {
            addCriterion("tone_analyze >", value, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tone_analyze >=", value, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeLessThan(Integer value) {
            addCriterion("tone_analyze <", value, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeLessThanOrEqualTo(Integer value) {
            addCriterion("tone_analyze <=", value, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeIn(List<Integer> values) {
            addCriterion("tone_analyze in", values, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeNotIn(List<Integer> values) {
            addCriterion("tone_analyze not in", values, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeBetween(Integer value1, Integer value2) {
            addCriterion("tone_analyze between", value1, value2, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andToneAnalyzeNotBetween(Integer value1, Integer value2) {
            addCriterion("tone_analyze not between", value1, value2, "toneAnalyze");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIsNull() {
            addCriterion("record_status is null");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIsNotNull() {
            addCriterion("record_status is not null");
            return (Criteria) this;
        }

        public Criteria andRecordStatusEqualTo(Integer value) {
            addCriterion("record_status =", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotEqualTo(Integer value) {
            addCriterion("record_status <>", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusGreaterThan(Integer value) {
            addCriterion("record_status >", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("record_status >=", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLessThan(Integer value) {
            addCriterion("record_status <", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLessThanOrEqualTo(Integer value) {
            addCriterion("record_status <=", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIn(List<Integer> values) {
            addCriterion("record_status in", values, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotIn(List<Integer> values) {
            addCriterion("record_status not in", values, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusBetween(Integer value1, Integer value2) {
            addCriterion("record_status between", value1, value2, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("record_status not between", value1, value2, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andCallResultIsNull() {
            addCriterion("call_result is null");
            return (Criteria) this;
        }

        public Criteria andCallResultIsNotNull() {
            addCriterion("call_result is not null");
            return (Criteria) this;
        }

        public Criteria andCallResultEqualTo(Integer value) {
            addCriterion("call_result =", value, "callResult");
            return (Criteria) this;
        }

        public Criteria andCallResultNotEqualTo(Integer value) {
            addCriterion("call_result <>", value, "callResult");
            return (Criteria) this;
        }

        public Criteria andCallResultGreaterThan(Integer value) {
            addCriterion("call_result >", value, "callResult");
            return (Criteria) this;
        }

        public Criteria andCallResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("call_result >=", value, "callResult");
            return (Criteria) this;
        }

        public Criteria andCallResultLessThan(Integer value) {
            addCriterion("call_result <", value, "callResult");
            return (Criteria) this;
        }

        public Criteria andCallResultLessThanOrEqualTo(Integer value) {
            addCriterion("call_result <=", value, "callResult");
            return (Criteria) this;
        }

        public Criteria andCallResultIn(List<Integer> values) {
            addCriterion("call_result in", values, "callResult");
            return (Criteria) this;
        }

        public Criteria andCallResultNotIn(List<Integer> values) {
            addCriterion("call_result not in", values, "callResult");
            return (Criteria) this;
        }

        public Criteria andCallResultBetween(Integer value1, Integer value2) {
            addCriterion("call_result between", value1, value2, "callResult");
            return (Criteria) this;
        }

        public Criteria andCallResultNotBetween(Integer value1, Integer value2) {
            addCriterion("call_result not between", value1, value2, "callResult");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}