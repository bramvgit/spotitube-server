package nl.han.oose.dea.resource.filter;

import nl.han.oose.dea.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

class RequestFilterTest {
    private RequestFilter sut;
    private UserService mockedUserService;

    @BeforeEach
    void setUp() {
        mockedUserService = Mockito.mock(UserService.class);

        sut = new RequestFilter();
        sut.setUserService(mockedUserService);
    }

    @Test
    void filterCallsVerifyToken() {
        // Arrange
        String key = "token";
        String value = "token";

        // Arrange mocks
        ContainerRequestContext mockedContainerRequestContext = Mockito.mock(ContainerRequestContext.class);
        UriInfo mockedUriInfo = Mockito.mock(UriInfo.class);
        MultivaluedMap<String, String> queryParameters = Mockito.mock(MultivaluedMap.class);

        // Arrange mock responses
        Mockito.when(mockedContainerRequestContext.getUriInfo()).thenReturn(mockedUriInfo);
        Mockito.when(mockedUriInfo.getQueryParameters()).thenReturn(queryParameters);
        Mockito.when(queryParameters.containsKey(key)).thenReturn(true);
        Mockito.when(queryParameters.getFirst(key)).thenReturn(value);

        // Act
        sut.filter(mockedContainerRequestContext);

        // Assert
        Mockito.verify(mockedUserService).verifyToken(value);
    }
}