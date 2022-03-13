package kata19dfs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class Kata19DFSController {

    @GetMapping("/kata19")
    @ResponseBody
    public List<List<String>> findChains(@RequestParam(name = "sourceWord") String sourceWord, @RequestParam(name = "targetWord") String targetWord) {
        WordChainFinder finder = new WordChainFinder(sourceWord, targetWord);
        LocalDateTime startTime = LocalDateTime.now();
        List<List<String>> answer = finder.findShortestChains();
        LocalDateTime endTime = LocalDateTime.now();
        double processingTime = ChronoUnit.MILLIS.between(startTime, endTime) / 1000.0;
        System.out.println("Answer found in " + processingTime + " seconds");
        return answer;
    }
}
