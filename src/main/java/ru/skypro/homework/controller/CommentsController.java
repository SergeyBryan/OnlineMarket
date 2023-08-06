package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.models.Advertisement;


@Slf4j
@RestController
@RequestMapping("/ads/{adId}/comments")
@CrossOrigin(value = "http://localhost:3000")
public class CommentsController {


    @Operation(
            summary = "Get Ad comments",
            description = "Get Ad comments by ID Ad"
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
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ListCommentsDTO> getAdComments(@PathVariable String adId) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Add comment to ad",
            description = "Add comment to ad"
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
                    )
            })
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CommentDTO> addCommentToAd(@RequestBody CreateOrUpdateCommentDTO adDTO) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Delete comment",
            description = "Delete comment by ID"
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
    @DeleteMapping(value = "/{commentId}")
    public ResponseEntity<Advertisement> deleteAd(@PathVariable String adId,
                                                  @PathVariable String commentId) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Update comment",
            description = "Update comment"
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
            value = "/{commentId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CommentDTO> updateAd(@PathVariable String adId,
                                               @PathVariable String commentId,
                                               @RequestBody CreateOrUpdateCommentDTO adDTO) {
        return ResponseEntity.ok().build();
    }
}
