package io.nebulas.explorer.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NasAccountCondition {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitOffset = -1;

    protected int limitSize = 1;

    public NasAccountCondition() {
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

    public void setLimitOffset(int limitOffset) {
        this.limitOffset = limitOffset;
    }

    public void setLimitSize(int limitSize) {
        this.limitSize = limitSize;
    }

    public int getLimitOffset() {
        return limitOffset;
    }

    public int getLimitSize() {
        return limitSize;
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAddressCountIsNull() {
            addCriterion("address_count is null");
            return (Criteria) this;
        }

        public Criteria andAddressCountIsNotNull() {
            addCriterion("address_count is not null");
            return (Criteria) this;
        }

        public Criteria andAddressCountEqualTo(Integer value) {
            addCriterion("address_count =", value, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressCountNotEqualTo(Integer value) {
            addCriterion("address_count <>", value, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressCountGreaterThan(Integer value) {
            addCriterion("address_count >", value, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_count >=", value, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressCountLessThan(Integer value) {
            addCriterion("address_count <", value, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressCountLessThanOrEqualTo(Integer value) {
            addCriterion("address_count <=", value, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressCountIn(List<Integer> values) {
            addCriterion("address_count in", values, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressCountNotIn(List<Integer> values) {
            addCriterion("address_count not in", values, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressCountBetween(Integer value1, Integer value2) {
            addCriterion("address_count between", value1, value2, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressCountNotBetween(Integer value1, Integer value2) {
            addCriterion("address_count not between", value1, value2, "addressCount");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementIsNull() {
            addCriterion("address_increment is null");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementIsNotNull() {
            addCriterion("address_increment is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementEqualTo(Integer value) {
            addCriterion("address_increment =", value, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementNotEqualTo(Integer value) {
            addCriterion("address_increment <>", value, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementGreaterThan(Integer value) {
            addCriterion("address_increment >", value, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_increment >=", value, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementLessThan(Integer value) {
            addCriterion("address_increment <", value, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementLessThanOrEqualTo(Integer value) {
            addCriterion("address_increment <=", value, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementIn(List<Integer> values) {
            addCriterion("address_increment in", values, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementNotIn(List<Integer> values) {
            addCriterion("address_increment not in", values, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementBetween(Integer value1, Integer value2) {
            addCriterion("address_increment between", value1, value2, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andAddressIncrementNotBetween(Integer value1, Integer value2) {
            addCriterion("address_increment not between", value1, value2, "addressIncrement");
            return (Criteria) this;
        }

        public Criteria andContractCountIsNull() {
            addCriterion("contract_count is null");
            return (Criteria) this;
        }

        public Criteria andContractCountIsNotNull() {
            addCriterion("contract_count is not null");
            return (Criteria) this;
        }

        public Criteria andContractCountEqualTo(Integer value) {
            addCriterion("contract_count =", value, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractCountNotEqualTo(Integer value) {
            addCriterion("contract_count <>", value, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractCountGreaterThan(Integer value) {
            addCriterion("contract_count >", value, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("contract_count >=", value, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractCountLessThan(Integer value) {
            addCriterion("contract_count <", value, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractCountLessThanOrEqualTo(Integer value) {
            addCriterion("contract_count <=", value, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractCountIn(List<Integer> values) {
            addCriterion("contract_count in", values, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractCountNotIn(List<Integer> values) {
            addCriterion("contract_count not in", values, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractCountBetween(Integer value1, Integer value2) {
            addCriterion("contract_count between", value1, value2, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractCountNotBetween(Integer value1, Integer value2) {
            addCriterion("contract_count not between", value1, value2, "contractCount");
            return (Criteria) this;
        }

        public Criteria andContractIncrementIsNull() {
            addCriterion("contract_increment is null");
            return (Criteria) this;
        }

        public Criteria andContractIncrementIsNotNull() {
            addCriterion("contract_increment is not null");
            return (Criteria) this;
        }

        public Criteria andContractIncrementEqualTo(Integer value) {
            addCriterion("contract_increment =", value, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andContractIncrementNotEqualTo(Integer value) {
            addCriterion("contract_increment <>", value, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andContractIncrementGreaterThan(Integer value) {
            addCriterion("contract_increment >", value, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andContractIncrementGreaterThanOrEqualTo(Integer value) {
            addCriterion("contract_increment >=", value, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andContractIncrementLessThan(Integer value) {
            addCriterion("contract_increment <", value, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andContractIncrementLessThanOrEqualTo(Integer value) {
            addCriterion("contract_increment <=", value, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andContractIncrementIn(List<Integer> values) {
            addCriterion("contract_increment in", values, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andContractIncrementNotIn(List<Integer> values) {
            addCriterion("contract_increment not in", values, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andContractIncrementBetween(Integer value1, Integer value2) {
            addCriterion("contract_increment between", value1, value2, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andContractIncrementNotBetween(Integer value1, Integer value2) {
            addCriterion("contract_increment not between", value1, value2, "contractIncrement");
            return (Criteria) this;
        }

        public Criteria andTimestampIsNull() {
            addCriterion("timestamp is null");
            return (Criteria) this;
        }

        public Criteria andTimestampIsNotNull() {
            addCriterion("timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andTimestampEqualTo(Date value) {
            addCriterion("timestamp =", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotEqualTo(Date value) {
            addCriterion("timestamp <>", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampGreaterThan(Date value) {
            addCriterion("timestamp >", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("timestamp >=", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampLessThan(Date value) {
            addCriterion("timestamp <", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampLessThanOrEqualTo(Date value) {
            addCriterion("timestamp <=", value, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampIn(List<Date> values) {
            addCriterion("timestamp in", values, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotIn(List<Date> values) {
            addCriterion("timestamp not in", values, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampBetween(Date value1, Date value2) {
            addCriterion("timestamp between", value1, value2, "timestamp");
            return (Criteria) this;
        }

        public Criteria andTimestampNotBetween(Date value1, Date value2) {
            addCriterion("timestamp not between", value1, value2, "timestamp");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
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