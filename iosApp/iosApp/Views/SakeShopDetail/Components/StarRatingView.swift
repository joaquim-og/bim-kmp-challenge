import SwiftUI

struct StarRatingView: View {
    let rating: Double
    let maxRating: Int = 5
    let starSize: CGFloat
    let filledColor: Color
    let emptyColor: Color
    
    init(
        rating: Double,
        starSize: CGFloat = 14,
        filledColor: Color = .yellow,
        emptyColor: Color = .gray.opacity(0.3)
    ) {
        self.rating = rating
        self.starSize = starSize
        self.filledColor = filledColor
        self.emptyColor = emptyColor
    }
    
    var body: some View {
        HStack(spacing: 2) {
            ForEach(0..<maxRating, id: \.self) { index in
                StarView(
                    fillAmount: getFillAmount(for: index),
                    size: starSize,
                    filledColor: filledColor,
                    emptyColor: emptyColor
                )
            }
        }
    }
    
    private func getFillAmount(for index: Int) -> Double {
        let starNumber = Double(index + 1)
        if rating >= starNumber {
            return 1.0
        } else if rating > Double(index) {
            return rating - Double(index)
        } else {
            return 0.0
        }
    }
}

struct StarView: View {
    let fillAmount: Double
    let size: CGFloat
    let filledColor: Color
    let emptyColor: Color
    
    var body: some View {
        ZStack {
          
            Image(systemName: "star.fill")
                .foregroundColor(emptyColor)
                .font(.system(size: size))
            
            Image(systemName: "star.fill")
                .foregroundColor(filledColor)
                .font(.system(size: size))
                .mask(
                    GeometryReader { geometry in
                        Rectangle()
                            .frame(width: geometry.size.width * fillAmount)
                    }
                )
        }
    }
}
