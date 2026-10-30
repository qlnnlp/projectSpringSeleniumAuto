package models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsersListResponse(
        Integer page,
        @JsonProperty("per_page")
        Integer perPage,
        Integer total,
        @JsonProperty("total_pages")
        Integer totalPages,
        List<UserResponse.Data> data,
        UserResponse.Support support
) {}