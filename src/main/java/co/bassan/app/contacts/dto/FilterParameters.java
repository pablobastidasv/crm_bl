package co.bassan.app.contacts.dto;

import lombok.Data;

import javax.ws.rs.QueryParam;

/**
 * Created by pbastidas on 3/5/16.
 */
@Data
public class FilterParameters {

    @QueryParam("firstName")
    private String firstName;
    @QueryParam("lastName")
    private String lastName;
    @QueryParam("qtyPerPage")
    private Integer qtyPerPage;
    @QueryParam("page")
    private Integer page;
}
