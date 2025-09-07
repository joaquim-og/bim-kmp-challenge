import SwiftUI
import Shared

class FlowCollector<T>: Kotlinx_coroutines_coreFlowCollector {
    private let callback: (T?) -> Void
    
    init(callback: @escaping (T?) -> Void) {
        self.callback = callback
    }
    
    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        callback(value as? T)
        completionHandler(nil)
    }
    
    func emit(value: Any?) async throws {
        callback(value as? T)
    }
}

extension ObservableObject where Self: AnyObject {
    @MainActor
    func observeFlow<T>(
        _ flow: Kotlinx_coroutines_coreFlow,
        updateHandler: @escaping (T) -> Void
    ) -> Task<Void, Never> {
        Task { [weak self] in
            guard self != nil else { return }
            
            do {
                let collector = FlowCollector<T> { value in
                    guard let value = value else { return }
                    Task { @MainActor in
                        updateHandler(value)
                    }
                }
                try await flow.collect(collector: collector)
            } catch {
                print("Error collecting flow: \(error)")
            }
        }
    }
}
