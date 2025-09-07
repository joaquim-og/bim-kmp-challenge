import SwiftUI
import Shared
import Combine

@MainActor
class SakeShopDetailViewModelWrapper: ObservableObject {
    private let viewModel: SakeShopDetailViewModel
    @Published var selectedSakeShop: SakeShop?
    
    private var observationTask: Task<Void, Never>?
    
    init() {
        self.viewModel = DIHelper.shared.getSakeShopDetailViewModel()
        observationTask = observeFlow(viewModel.selectedSakeShop) { [weak self] (shop: SakeShop?) in
            self?.selectedSakeShop = shop
        }
    }
    
    func selectSakeShop(_ sakeShop: SakeShop?) {
        viewModel.onSelectSakeShop(sakeShop: sakeShop)
    }
    
    deinit {
        observationTask?.cancel()
    }
}
