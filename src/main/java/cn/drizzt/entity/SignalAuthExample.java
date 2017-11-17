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

        public Criteria andCallStatusIsNull() {
            addCriterion("call_status is null");
            return (Criteria) this;
        }

        public Criteria andCallStatusIsNotNull() {
            addCriterion("call_status is not null");
            return (Criteria) this;
        }

        public Criteria andCallStatusEqualTo(Integer value) {
            addCriterion("call_status =", value, "callStatus");
            return (Criteria) this;
        }

        public Criteria andCallStatusNotEqualTo(Integer value) {
            addCriterion("call_status <>", value, "callStatus");
            return (Criteria) this;
        }

        public Criteria andCallStatusGreaterThan(Integer value) {
            addCriterion("call_status >", value, "callStatus");
            return (Criteria) this;
        }

        public Criteria andCallStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("call_status >=", value, "callStatus");
            return (Criteria) this;
        }

        public Criteria andCallStatusLessThan(Integer value) {
            addCriterion("call_status <", value, "callStatus");
            return (Criteria) this;
        }

        public Criteria andCallStatusLessThanOrEqualTo(Integer value) {
            addCriterion("call_status <=", value, "callStatus");
            return (Criteria) this;
        }

        public Criteria andCallStatusIn(List<Integer> values) {
            addCriterion("call_status in", values, "callStatus");
            return (Criteria) this;
        }

        public Criteria andCallStatusNotIn(List<Integer> values) {
            addCriterion("call_status not in", values, "callStatus");
            return (Criteria) this;
        }

        public Criteria andCallStatusBetween(Integer value1, Integer value2) {
            addCriterion("call_status between", value1, value2, "callStatus");
            return (Criteria) this;
        }

        public Criteria andCallStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("call_status not between", value1, value2, "callStatus");
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