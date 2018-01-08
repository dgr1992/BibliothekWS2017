class Category {
  id: string;
  categoryIndex: string;
  categoryName: string;
  room: string;
}

export class Copy {
  id: string;
  mediumId: string;
  copyNumber: string;
  mediaType: string;
  rental: string;
  category: Category;
  copyStatus: string;
}
