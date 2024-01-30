package online.mokkoji.result.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.result.domain.Result;
import online.mokkoji.result.repository.ResultRepository;
import online.mokkoji.user.domain.Provider;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;

    @Override
    public List<Result> readResult(String provider, String email)  {
        List<Result> result = resultRepository.findAllByProviderAndEmail(Provider.valueOf(provider), email);


    }
}
