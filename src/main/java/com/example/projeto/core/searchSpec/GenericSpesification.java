
package com.example.projeto.core.searchSpec;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Generic spesification.
 *
 * @param <T> the type parameter
 */
@Getter
public class GenericSpesification<T> implements Specification<T> {

    private static final long serialVersionUID = 1900581010229669687L;

    private List<SearchCriteria> list;
    
    private List<JoinCriteria> listJoin;

    /**
     * Instantiates a new Generic spesification.
     */
    public GenericSpesification() {
        this.list = new ArrayList<>();
        this.listJoin = new ArrayList<JoinCriteria>();
    }

    /**
     * Add.
     *
     * @param criteria the criteria
     */
    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    /**
     * Add join.
     *
     * @param JoinCriteria the join criteria
     */
    public void addJoin(JoinCriteria JoinCriteria) {
        listJoin.add(JoinCriteria);
    }

    @SneakyThrows
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(builder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            }else{
                throw new Exception("Invalid Search Operation");
            }
        }

        for (JoinCriteria joinCriteria : listJoin) {
            String tablename = joinCriteria.getTableName();
            List<SearchCriteria> specList = ((GenericSpesification) joinCriteria.getSpecification()).getList();
            for(SearchCriteria criteria : specList){

                if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                    predicates.add(builder.greaterThan(
                            root.join(tablename).get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                    predicates.add(builder.lessThan(
                            root.join(tablename).get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                    predicates.add(builder.greaterThanOrEqualTo(
                            root.join(tablename).get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                    predicates.add(builder.lessThanOrEqualTo(
                            root.join(tablename).get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                    predicates.add(builder.notEqual(
                            root.join(tablename).get(criteria.getKey()), criteria.getValue()));
                } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                    predicates.add(builder.equal(
                            root.join(tablename).get(criteria.getKey()), criteria.getValue()));
                } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                    predicates.add(builder.like(
                            builder.lower(root.join(tablename).get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                    predicates.add(builder.like(
                            builder.lower(root.join(tablename).get(criteria.getKey())),
                            criteria.getValue().toString().toLowerCase() + "%"));
                }else{
                    throw new Exception("Invalid Search Operation");
                }

            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }


}