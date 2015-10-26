package com.hkesports.matchticker.repository.factory;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * @author manboyu
 *
 * @param <R>
 * @param <T>
 * @param <I>
 */
public class GenericRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

	@SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {

        return new GenericRepositoryFactory(entityManager);
    }

    private static class GenericRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public GenericRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        @SuppressWarnings("unchecked")
		protected Object getTargetRepository(RepositoryMetadata metadata) {
            return new GenericRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            // to check for QueryDslJpaRepository's which is out of scope.
            return GenericRepository.class;
        }
    }
}
