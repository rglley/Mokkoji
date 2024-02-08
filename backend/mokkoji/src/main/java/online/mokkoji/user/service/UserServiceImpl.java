public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RecordRepository recordRepository;
    private final JwtUtil jwtService;

    @Override
    public UpdatePageResDto getUpdatePage(String provider, String email) {
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        User readUser = findUser.get();
        UserAccount userAccount = readUser.getUserAccount();

        return UpdatePageResDto.builder()
                .email(readUser.getEmail())
                .image(readUser.getImage())
                .name(readUser.getName())
                .bank(userAccount.getBank())
                .accountNumber(userAccount.getNumber())
                .build();
    }

    @Override
    public MyPageResDto getMypage(String provider, String email) {
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        User readUser = findUser.get();
        UserAccount userAccount = readUser.getUserAccount();
        Record record = readUser.getRecord();

        if (userAccount.getBank().equals("") || userAccount.getNumber().equals("")) {
            return MyPageResDto.builder()
                    .image(readUser.getImage())
                    .name(readUser.getName())
                    .isAccountRegistered(false)
                    .eventCount(record.getEventCount())
                    .totalTime(record.getTotalTime())
                    .totalParticipant(record.getTotalParticipant())
                    .totalMessage(record.getTotalMessage())
                    .build();
        }

        return MyPageResDto.builder()
                .image(readUser.getImage())
                .name(readUser.getName())
                .isAccountRegistered(true)
                .eventCount(record.getEventCount())
                .totalTime(record.getTotalTime())
                .totalParticipant(record.getTotalParticipant())
                .totalMessage(record.getTotalMessage())
                .build();
    }

    @Override
    public void createUser(String provider, String email, UserInputReqDto userInputReqDto) {
        Optional<User> findUser = userRepository.findByProviderAndEmail
                (Provider.valueOf(provider), email);

        if(findUser.isPresent() && findUser.get().getAuthority().getKey().equals("ROLE_USER")) {
            throw new RestApiException(UserErrorCode.DUPLICATE_SIGNUP);
        }

        String refreshToken = jwtService.createRefreshToken();

        User newUser = findUser.get();
        newUser.updateAuthority();
        newUser.updateRefreshToken(refreshToken);

        Record record = Record.builder()
                .user(newUser)
                .eventCount(0)
                .totalMessage(0)
                .totalParticipant(0)
                .totalTime(0)
                .build();

        userRepository.save(newUser);
        recordRepository.save(record);

        String bank = userInputReqDto.getBank();
        String accountNumber = userInputReqDto.getAccountNumber();

        UserAccount userAccount = UserAccount.builder()
                .user(newUser)
                .bank(bank)
                .number(accountNumber)
                .build();

        accountRepository.save(userAccount);
    }

    @Override
    public void updateUser(String provider, String email, UserInputReqDto modifyDto) {
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        String name = modifyDto.getName();
        String image = modifyDto.getImage();
        String bank = modifyDto.getBank();
        String accountNumber = modifyDto.getAccountNumber();

        User updateUser = findUser.get();
        updateUser.updateUser(name, image);
        userRepository.save(updateUser);

        Optional<UserAccount> findAccount =
                accountRepository.findByUser_ProviderAndUser_Email(updateUser.getProvider(), updateUser.getEmail());

        UserAccount userAccount = findAccount.get();
        userAccount.updateAccount(bank, accountNumber);

        accountRepository.save(userAccount);
    }

    @Override
    public void deleteUser(String provider, String email) {
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        User deleteUser = findUser.get();
        userRepository.delete(deleteUser);
    }
}
