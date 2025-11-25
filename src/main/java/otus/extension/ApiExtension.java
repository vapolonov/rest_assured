package otus.extension;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import otus.modules.ApiModule;

public class ApiExtension implements BeforeEachCallback {

  private Injector injector;

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    if (injector == null) {
      injector = Guice.createInjector(new ApiModule());
    }

    Object testInstance = context.getRequiredTestInstance();
    injector.injectMembers(testInstance);

  }
}
