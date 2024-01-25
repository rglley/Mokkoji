package online.mokkoji.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionsResDto {
    private Number numberOfElements;
//    private List<ConnectionObjectDto> content;

    //임시 생성자 -> 수정 필요
    //Connection List를 받은 후, 갯수와 active 상태를 구해야 함
    public ConnectionsResDto(int id) {
        this.numberOfElements = 0;

    }
}
