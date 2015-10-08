package commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;

public class ep { // extends BaseCommand {

    // @Command
    // @Usage("ls")
    public String list() {

        RestTemplate template = new RestTemplate();
        ResponseEntity<Connection[]> responseEntity = template.getForEntity("http://localhost:5000/registrations/eventprocessing", Connection[].class);

        List<Connection> connections = Arrays.asList(responseEntity.getBody());

        StringBuilder sb = new StringBuilder();

        if (CollectionUtils.isEmpty(connections)) {
            sb.append("No entry");
        } else {
            for (Connection connection : connections) {
                sb.append(connection.toString());
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
