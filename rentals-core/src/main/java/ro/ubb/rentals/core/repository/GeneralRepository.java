package ro.ubb.rentals.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.rentals.core.model.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
@Transactional
public interface GeneralRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID>, QueryByExampleExecutor<T>, PagingAndSortingRepository<T, ID> {
}
