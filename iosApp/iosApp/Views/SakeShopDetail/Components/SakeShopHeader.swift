import SwiftUI

struct SakeShopHeader: View {
    let name: String
    let rating: Double
    
    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            Text(name)
                .font(.largeTitle)
                .fontWeight(.bold)
                .foregroundColor(.primary)
            
            HStack(spacing: 12) {
                StarRatingView(rating: rating)
                
                Text(String(format: "%.1f", rating))
                    .font(.body)
                    .fontWeight(.semibold)
                    .foregroundColor(.primary)
                
                Text("out of 5")
                    .font(.body)
                    .foregroundColor(.secondary)
            }
        }
    }
}
