import Shared

class DIHelper {
    static let shared = DIHelper()
    private let koinHelper = KoinHelper()
    
    private init() {
        if KoinPlatform.shared.getKoinOrNull() == nil {
            KoinIOSKt.doInitKoinIos()
        }
    }
    
    func getSakeShopListViewModel() -> SakeShopListViewModel {
        return koinHelper.getSakeShopListViewModel()
    }
    
    func getSakeShopDetailViewModel() -> SakeShopDetailViewModel {
        return koinHelper.getSakeShopDetailViewModel()
    }
    
    func getPlatformActions() -> PlatformActions {
        return koinHelper.getPlatformActions()
    }
}
