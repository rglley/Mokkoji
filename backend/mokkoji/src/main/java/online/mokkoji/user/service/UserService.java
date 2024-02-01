package online.mokkoji.user.service;

import online.mokkoji.user.dto.response.UpdatePageResDto;

public interface UserService {
    UpdatePageResDto readUpdatePage(String provider, String email);
}
