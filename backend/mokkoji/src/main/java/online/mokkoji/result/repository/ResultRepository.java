package online.mokkoji.result.repository;

import online.mokkoji.result.domain.Result;
import online.mokkoji.user.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findAllByUser_ProviderAndUser_EmailOrderByIdDesc(Provider provider, String email);
}