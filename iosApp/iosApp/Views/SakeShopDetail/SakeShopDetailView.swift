import SwiftUI
import Shared

struct SakeShopDetailView: View {
    let sakeShop: SakeShop
    @Environment(\.dismiss) private var dismiss
    private let platformActions = DIHelper.shared.getPlatformActions()
    
    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                SakeShopImage(
                    imageUrl: sakeShop.picture,
                    shopName: sakeShop.name
                )
                
                VStack(alignment: .leading, spacing: 20) {
                    SakeShopHeader(
                        name: sakeShop.name,
                        rating: sakeShop.rating
                    )
                    
                    SakeShopDescriptionSection(
                        description: sakeShop.shopDescription
                    )
                    
                    SakeShopAddressSection(
                        address: sakeShop.address,
                        onTap: {
                            platformActions.openMap(address: sakeShop.address)
                        }
                    )
                    
                    if let website = sakeShop.website {
                        SakeShopWebsiteSection(
                            website: website,
                            onTap: {
                                platformActions.openWebsite(url: website)
                            }
                        )
                    }
                }
                .padding(24)
            }
        }
        .navigationBarTitleDisplayMode(.inline)
    }
}

// Preview
#Preview {
    NavigationStack {
        SakeShopDetailView(
            sakeShop: SakeShop(
                name: "信州スシサカバ 寿しなの",
                shopDescription: "A traditional sushi bar specializing in fresh sashimi and an extensive collection of premium sake from the Nagano region. Our master chef has over 20 years of experience and carefully selects the finest ingredients.",
                picture: "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
                rating: 4.5,
                address: "〒380-0824 長野県長野市南長野南石堂町1421",
                coordinates: [36.644257, 138.18668],
                googleMapsLink: "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6",
                website: "https://www.sushinano.com/"
            )
        )
    }
}
