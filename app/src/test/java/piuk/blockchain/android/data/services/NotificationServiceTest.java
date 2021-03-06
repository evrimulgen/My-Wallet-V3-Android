package piuk.blockchain.android.data.services;

import info.blockchain.wallet.api.WalletApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import okhttp3.ResponseBody;
import piuk.blockchain.android.RxTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class NotificationServiceTest extends RxTest {

    private NotificationService subject;
    @Mock private WalletApi mockWalletApi;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        subject = new NotificationService(mockWalletApi);
    }

    @Test
    public void sendNotificationToken() throws Exception {
        // Arrange
        when(mockWalletApi.updateFirebaseNotificationToken("", "", ""))
                .thenReturn(Observable.just(mock(ResponseBody.class)));
        // Act
        TestObserver<Void> testObserver = subject.sendNotificationToken("", "", "").test();
        // Assert
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        verify(mockWalletApi).updateFirebaseNotificationToken("", "", "");
        verifyNoMoreInteractions(mockWalletApi);
    }

}