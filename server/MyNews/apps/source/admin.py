from django.contrib import admin

from .models import Source, Category, SourceCategory, UserCategory


# Register your models here.

class SouceCateogryInLine(admin.StackedInline):
    model = SourceCategory
    extra = 1


class CategoryAdmin(admin.ModelAdmin):
    inlines = [SouceCateogryInLine]
    list_display = ('id', 'name')


class SourceCategoryAdmin(admin.ModelAdmin):
    list_display = ('id', 'source', 'category')


class SourceAdmin(admin.ModelAdmin):
    inlines = [SouceCateogryInLine]
    list_display = ('id', 'name', 'host', 'calification')


admin.site.register(Source, SourceAdmin)
admin.site.register(Category, CategoryAdmin)
admin.site.register(SourceCategory, SourceCategoryAdmin)
# admin.site.register(UserCategory)
