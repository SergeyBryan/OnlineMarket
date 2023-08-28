package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ListAdsDTO;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.service.AdService;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @Operation(
            summary = "Get all ads",
            description = "Get all ads"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListAdsDTO> getAllAds() {
        ListAdsDTO allAds = adService.getAllAds();
        return ResponseEntity.ok(allAds);
    }

    @Operation(
            summary = "Add ad",
            description = "Add ad"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @PostMapping(
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<AdDTO> addAd(@RequestPart("properties") CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                      @RequestPart("image") MultipartFile image) throws IOException {
        log.info("AdController, adAdd method process. Request include: " + createOrUpdateAdDTO + " " + image.getContentType());
        AdDTO adDTO = adService.addAd(createOrUpdateAdDTO, image);
        return ResponseEntity.ok(adDTO);
    }

    @Operation(
            summary = "Get Advertisement",
            description = "Get Ad info by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            })
    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<AdDTO> getAdvertisement(@PathVariable String id) throws NotFoundException {
        AdDTO ad = adService.getAd(id);
        return ResponseEntity.ok(ad);
    }

    @Operation(
            summary = "Delete Advertisement",
            description = "Delete Advertisement by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable String id) {
        adService.deleteAd(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Update ad",
            description = "Update info about ad"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            })
    @PatchMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<AdDTO> updateAd(@RequestBody CreateOrUpdateAdDTO adDTO,
                                                        @PathVariable String id) {
        AdDTO updatedAd = adService.updateAd(adDTO, id);
        return ResponseEntity.ok(updatedAd);
    }

    @Operation(
            summary = "Get users ad",
            description = "Get all users ad"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @GetMapping(
            value = "/me",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ListAdsDTO> getUsersAds() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("username: " + username);
        Object principal = authentication.getPrincipal();
        log.info("principal: " + principal);
        ListAdsDTO usersAds = adService.getUsersAds(username);
        return ResponseEntity.ok(usersAds);
    }

    @Operation(
            summary = "Update image",
            description = "Update image"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            })
    @PatchMapping(
            value = "/{id}/image",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE}
    )
    @ResponseBody
    public ResponseEntity<InputStreamResource> updateImage(@PathVariable String id,
                                                           @RequestPart("image") MultipartFile image) throws IOException {
        var inputStreamResource = adService.updateAdsImage(id, image);
        return ResponseEntity.ok(inputStreamResource);
    }

}
