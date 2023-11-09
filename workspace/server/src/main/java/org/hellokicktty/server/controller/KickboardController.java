package org.hellokicktty.server.controller;

import lombok.RequiredArgsConstructor;
import org.hellokicktty.server.dto.KickboardResponseDto;
import org.hellokicktty.server.service.KickboardService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("kickboards")
public class KickboardController {


    private final KickboardService kickboardService;

    @GetMapping
    List<KickboardResponseDto> findKickboardsInRange(Double lat, Double lng) {
        List<KickboardResponseDto> dto = new ArrayList<>();
        kickboardService.findKickboardsInRange(lat, lng)
                .stream()
                .forEach(item -> dto.add(new KickboardResponseDto(item)));
        return dto;
    }

    @GetMapping("/{id}")
    KickboardResponseDto findKickboard(@PathVariable Long id) {
        return new KickboardResponseDto(kickboardService.findKickboard(id));
    }

    @PostMapping
    KickboardResponseDto addKickboard(Long id, Double lat, Double lng) {
        return new KickboardResponseDto(kickboardService.addKickboard(id, lat, lng));
    }

    @DeleteMapping("/{id}")
    void removeKickboard(@PathVariable Long id) {
        kickboardService.removeKickboard(id);
    }

}
