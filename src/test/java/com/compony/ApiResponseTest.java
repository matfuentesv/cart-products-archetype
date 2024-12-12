package com.compony;

import cl.company.exception.ApiResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void shouldCreateApiResponseWithSuccess() {

        String message = "Operation successful";
        boolean success = true;


        ApiResponse apiResponse = new ApiResponse(message, success);


        assertNotNull(apiResponse);
        assertEquals(message, apiResponse.getMessage());
        assertTrue(apiResponse.isSuccess());
    }

    @Test
    void shouldCreateApiResponseWithFailure() {
        // Given
        String message = "Operation failed";
        boolean success = false;

        // When
        ApiResponse apiResponse = new ApiResponse(message, success);

        // Then
        assertNotNull(apiResponse);
        assertEquals(message, apiResponse.getMessage());
        assertFalse(apiResponse.isSuccess());
    }



    @Test
    void shouldTestEqualsAndHashCode() {

        ApiResponse response1 = new ApiResponse("Same message", true);
        ApiResponse response2 = new ApiResponse("Same message", true);
        ApiResponse response3 = new ApiResponse("Different message", false);


        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void shouldTestToString() {

        ApiResponse apiResponse = new ApiResponse("Test message", true);
        String responseString = apiResponse.toString();
        assertNotNull(responseString);
        assertTrue(responseString.contains("Test message"));
        assertTrue(responseString.contains("true"));
    }

    @Test
    void testSetMessage() {
        ApiResponse apiResponse = new ApiResponse();
        String testMessage = "Operation successful";

        apiResponse.setMessage(testMessage);

        assertNotNull(apiResponse);
        assertEquals(testMessage, apiResponse.getMessage());
    }

    @Test
    void testSetSuccess() {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setSuccess(true);
        assertNotNull(apiResponse);
        assertTrue(apiResponse.isSuccess());

        apiResponse.setSuccess(false);
        assertNotNull(apiResponse);
        assertFalse(apiResponse.isSuccess());
    }

    @Test
    public void testEquals_sameObject() {
        ApiResponse response = new ApiResponse("Success", true);
        assertEquals(response, response); // Comparación con el mismo objeto
    }

    @Test
    public void testEquals_nullObject() {
        ApiResponse response = new ApiResponse("Success", true);
        assertNotEquals(null, response); // Comparación con null
    }

    @Test
    public void testEquals_differentClass() {
        ApiResponse response = new ApiResponse("Success", true);
        assertNotEquals("Not an ApiResponse", response); // Comparación con otro tipo
    }

    @Test
    public void testEquals_equalObjects() {
        ApiResponse response1 = new ApiResponse("Success", true);
        ApiResponse response2 = new ApiResponse("Success", true);
        assertEquals(response1, response2); // Objetos con los mismos valores
        assertEquals(response1.hashCode(), response2.hashCode()); // hashCode debe coincidir
    }

    @Test
    public void testEquals_differentValues() {
        ApiResponse response1 = new ApiResponse("Success", true);
        ApiResponse response2 = new ApiResponse("Failure", false);
        assertNotEquals(response1, response2); // Objetos con diferentes valores
        assertNotEquals(response1.hashCode(), response2.hashCode()); // hashCode no debe coincidir
    }

    @Test
    public void testEquals_differentMessageSameSuccess() {
        ApiResponse response1 = new ApiResponse("Success", true);
        ApiResponse response2 = new ApiResponse("Partial Success", true);
        assertNotEquals(response1, response2); // Diferente mensaje pero mismo estado
    }

    @Test
     void testToString() {
        ApiResponse response = new ApiResponse("Success", true);
        String toStringOutput = response.toString();

        assertTrue(toStringOutput.contains("Success"));
        assertTrue(toStringOutput.contains("true"));
    }

    @Test
     void testGetterAndSetter() {
        ApiResponse response = new ApiResponse();
        response.setMessage("Test Message");
        response.setSuccess(false);

        assertEquals("Test Message", response.getMessage());
        assertFalse(response.isSuccess());
    }

    @Test
     void testEquals_DifferentClass() {
        ApiResponse response = new ApiResponse("Success",true );
        String differentClassObject = "Not an ApiResponse";

        assertNotEquals(response, differentClassObject); // Debe retornar false porque son clases diferentes
    }
}